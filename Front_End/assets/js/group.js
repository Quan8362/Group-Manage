var listGroup = [];

var currentPage = 0;
var size = 5;
var sort = "groupId,asc";
var search = "";
var accLocal = JSON.parse(localStorage.getItem("accLogin"))
var userLogin = accLocal === null ? "" : accLocal.username;
var passLogin = accLocal === null ? "" : accLocal.password;
$(function() {
    reload();
})

function reload() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/group/getAll?page=" + currentPage + "&size=" + size + "&sort=" + sort + "&search=" + search,
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(`${userLogin}:${passLogin}`));
        },
        contentType: "JSON",
        success: function(response) {
            listGroup = [];
            listGroup = response.content;
            pagingTable(response.totalPages);
            $("#table_group").empty();
            for (let i = 0; i < listGroup.length; i++) {
                $("#table_group").append(
                    `<tr>
                    <td><label><input type="checkbox" class="checkbox-group" value="${listGroup[i].groupId}" id= "${i+1}"></label></td>
                    <td>${i+1}</td>
                    <td>${listGroup[i].groupName}</td>
                    <td>${listGroup[i].countMember}</td>
                    <td>${listGroup[i].fullName}</td>
                    <td>${listGroup[i].createDate}</td>
                    <td><a onclick="update(${listGroup[i].groupId})" class="edit-group"><i class="fas fa-solid fa-pen"></i></a></td>
                </tr>`
                );
            }
        }
    });
}

function pagingTable(pageAmount) {

    var pagingStr = "";

    if (pageAmount > 1 && currentPage > 0) {
        pagingStr +=
            `<li class="pagination-item">` +
            `<a href="#" class="pagination-item_link" onclick="prevPaging()"><i class="pagination-item_icon fas fa-chevron-left"></i></a>` +
            `</li>`;
    }

    for (i = 0; i < pageAmount; i++) {
        if (currentPage == i) {
            pagingStr +=
                `<li class="pagination-item pagination-item-active">` +
                `<a href="#" class="pagination-item_link" onclick="changePage(${i})">${i + 1}</a>` +
                `</li>`;
        } else {
            pagingStr +=
                `<li class="pagination-item">` +
                `<a href="#" class="pagination-item_link" onclick="changePage(${i})">${i + 1}</a>` +
                `</li>`;
        }

    }

    if (pageAmount > 1 && (currentPage + 1) < pageAmount) {
        pagingStr +=
            `<li class="pagination-item">` +
            `<a href="#" class="pagination-item_link" onclick="nextPaging()"><i class="pagination-item_icon fas fa-chevron-right"></i></a>` +
            `</li>`;
    }

    $('#pagination').empty();
    $('#pagination').append(pagingStr);

}

function prevPaging() {
    changePage(currentPage - 1);
}

function nextPaging() {
    changePage(currentPage + 1);
}

function changePage(page) {
    if (page == currentPage) {
        return;
    }
    currentPage = page;
    reload();
}

$("#search").click(function(e) {
    search = $("#inputSearch").val();
    reload();

});

// checkbox list group
$("#selectAll").click(function(e) {
    $("input[type=checkbox]").prop('checked', $(this).prop('checked'));
});


//delete list groups
function checkArray() {
    var list = document.querySelectorAll('input[type="checkbox"]:checked');
    var listSTT = [];
    for (let i = 0; i < list.length; i++) {

        listSTT.push(list[i].value);
    }
    return listSTT;
}

$("#delete").click(function(e) {
    var groupIds = checkArray();

    // call api de xoa cac du lieu do
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/group/delete",
        data: JSON.stringify(groupIds),
        contentType: "application/json",
        success: function(response) {
            alert("delete successfully!");
            reload();
        }
    });
});

$("#save").click(function(e) {
    var name = $("#groupName").val();

    if (!name || name.length < 6 || name.length > 30) {
        // show error message
        showNameErrorMessage("Group name must be from 6 to 50 characters!");
        return;
    } else {
        var group = {
            groupName: name,
            creatorId: accLocal.accountID
        }
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/group/add",
        data: JSON.stringify(group),
        contentType: "application/json",
        success: function(response) {
            alert("create successfully!");
            window.open("../Front_End/manage_group.html", "_self");
        }
    });

});

function update(id) {
    window.open("../Front_End/group-edit.html?id=" + id, "_self");
};

// reload list groups
$("#fresh").click(function(e) {
    window.open("../Front_End/manage_group.html", "_self");
});

function showNameErrorMessage(message) {
    document.getElementById("nameErrorMessage").style.display = "block";
    document.getElementById("nameErrorMessage").innerHTML = message;
}