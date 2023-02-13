package vti.com.vn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vti.com.vn.dto.GroupDTO;
import vti.com.vn.form.CreateGroupForm;
import vti.com.vn.form.UpdateGroupFrom;

import java.util.List;

public interface IGroupService {

    Page<GroupDTO> getAllGroup(Pageable pageable, String search);

    GroupDTO getByGroupId(Integer groupId);

    void deleteGroup(List<Integer> groupIds);

    void addGroup(CreateGroupForm form);

    void editGroup(UpdateGroupFrom form);
}
