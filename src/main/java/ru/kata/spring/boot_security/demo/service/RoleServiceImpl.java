//package ru.kata.spring.boot_security.demo.service;
//
//import org.springframework.stereotype.Service;
//import ru.kata.spring.boot_security.demo.dao.RoleDao;
//import ru.kata.spring.boot_security.demo.model.Role;
//
//import java.util.List;
//
//@Service
//public class RoleServiceImpl implements RoleService {
//    private final RoleDao roleDao;
//
//    public RoleServiceImpl(RoleDao roleDao) {
//        this.roleDao = roleDao;
//    }
//
//    @Override
//    public void add(Role role) {
//        roleDao.add(role);
//    }
//
//    @Override
//    public List<Role> getAllRoles() {
//        return roleDao.getAllRoles();
//    }
//
//    @Override
//    public List<Role> getRolesByUserId(Long id) {
//        return roleDao.getRolesByUserId(id);
//    }
//}
