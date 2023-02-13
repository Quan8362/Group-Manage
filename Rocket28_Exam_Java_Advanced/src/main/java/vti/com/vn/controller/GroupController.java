package vti.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vti.com.vn.dto.GroupDTO;
import vti.com.vn.form.CreateGroupForm;
import vti.com.vn.form.UpdateGroupFrom;
import vti.com.vn.service.IGroupService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("/getAll")
    public ResponseEntity<Page<GroupDTO>> getAll(Pageable pageable, @RequestParam(required = false) String search) {
        Page<GroupDTO> groupDTOS = groupService.getAllGroup(pageable, search);
        return new ResponseEntity<>(groupDTOS, HttpStatus.OK);
    }

    @GetMapping("/getByGroupId/{groupId}")
    public ResponseEntity<GroupDTO> getByGroupId(@PathVariable Integer groupId) {
        GroupDTO groupDTO = groupService.getByGroupId(groupId);
        return new ResponseEntity<>(groupDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addGroup(@RequestBody @Valid CreateGroupForm form){

        groupService.addGroup(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editGroup(@PathVariable Integer id, @Valid @RequestBody UpdateGroupFrom form) {
        form.setGroupId(id);
        groupService.editGroup(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteGroup(@RequestBody List<Integer> groupIds) {
        groupService.deleteGroup(groupIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
