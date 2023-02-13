package vti.com.vn.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vti.com.vn.dto.GroupDTO;
import vti.com.vn.entity.Group;
import vti.com.vn.form.CreateGroupForm;
import vti.com.vn.form.UpdateGroupFrom;
import vti.com.vn.repository.IUserRepository;
import vti.com.vn.repository.IGroupAccountRepository;
import vti.com.vn.repository.IGroupRepository;
import vti.com.vn.service.IGroupService;
import vti.com.vn.specification.GroupSpecification;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private IUserRepository accountRepository;

    @Autowired
    private IGroupAccountRepository groupAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<GroupDTO> getAllGroup(Pageable pageable, String search) {

        Specification<Group> where = GroupSpecification.buildWhere(search);

        Page<Group> groups = groupRepository.findAll(where, pageable);
        List<GroupDTO> groupDTOS = modelMapper.map(groups.getContent(), new TypeToken<List<GroupDTO>>() {
        }.getType());
        for (GroupDTO groupDTO : groupDTOS) {
            Group group = groupRepository.findById(groupDTO.getGroupId()).get();
            groupDTO.setCountMember(groupAccountRepository.countByGroup(group));
            groupDTO.setFullName(group.getAccount().getFullName());
        }

        return new PageImpl<>(groupDTOS, pageable, groupRepository.count());
    }

    @Override
    public GroupDTO getByGroupId(Integer groupId) {

        Group group = groupRepository.findById(groupId).get();
        GroupDTO groupDTO = modelMapper.map(group, new TypeToken<GroupDTO>() {
        }.getType());
        return groupDTO;
    }


    @Override
    @Transactional
    public void deleteGroup(List<Integer> groupIds) {
        for (Integer groupId : groupIds) {
            groupRepository.deleteById(groupId);
        }
    }

    @Override
    public void addGroup(CreateGroupForm form){
        // convert form to entity
        Group group = new Group();
        group.setGroupName(form.getGroupName());
        group.setAccount(accountRepository.findById(form.getCreatorId()).get());
        groupRepository.save(group);
    }


    @Override
    public void editGroup(UpdateGroupFrom form) {
        Group group = groupRepository.findById(form.getGroupId()).get();
        group.setGroupName(form.getGroupName());
        groupRepository.save(group);
    }

}
