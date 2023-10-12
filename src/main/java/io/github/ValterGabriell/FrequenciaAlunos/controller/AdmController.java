package io.github.ValterGabriell.FrequenciaAlunos.controller;


import io.github.ValterGabriell.FrequenciaAlunos.exceptions.RequestExceptions;
import io.github.ValterGabriell.FrequenciaAlunos.mapper.admin.CreateNewAdmin;
import io.github.ValterGabriell.FrequenciaAlunos.mapper.admin.UpdateAdminPassword;
import io.github.ValterGabriell.FrequenciaAlunos.mapper.admin.UpdateAdminUsername;
import io.github.ValterGabriell.FrequenciaAlunos.service.AdmService;
import io.github.ValterGabriell.FrequenciaAlunos.mapper.admin.GetAdminMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/admin")
public class AdmController {
    private final AdmService adminService;

    public AdmController(AdmService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(params = {"tenantId"})
    public ResponseEntity<String> insertAdmin(@RequestBody CreateNewAdmin insertAdmin, @RequestParam Integer tenantId) {
        var newAdmin = adminService.createNewAdmin(insertAdmin, tenantId);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    @GetMapping(params = {"adminSkId", "tenantId"})
    public ResponseEntity<GetAdminMapper> getAdminBySkId(
            @RequestParam String adminSkId,
            @RequestParam Integer tenantId) {
        GetAdminMapper admin = adminService.getAdminBySkId(adminSkId, tenantId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<Page<GetAdminMapper>> getAllAdmins(Pageable pageable) {
        var listAdmins = adminService.getAllAdmins(pageable);
        return new ResponseEntity<>(listAdmins, HttpStatus.OK);
    }

    @PutMapping(value = "update-username", params = {"adminId", "tenantId"})
    public ResponseEntity<GetAdminMapper> updateUsername(
            @RequestParam String adminId,
            @RequestBody UpdateAdminUsername updateAdminUsername,
            @RequestParam Integer tenantId) throws RequestExceptions {
        GetAdminMapper admin = adminService.updateAdminUsername(adminId, updateAdminUsername, tenantId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping(value = "update-password", params = {"adminId", "tenantId"})
    public ResponseEntity<GetAdminMapper> updatePassword(
            @RequestParam String adminId,
            @RequestBody UpdateAdminPassword updateAdminPassword,
            @RequestParam Integer tenantId) throws RequestExceptions {
        GetAdminMapper admin = adminService.updateAdminPassword(adminId, updateAdminPassword, tenantId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping(params = {"adminId", "tenantId"})
    public ResponseEntity<String> deleteAdminBySkId(@RequestParam String adminId, @RequestParam Integer tenantId) {
        String response = adminService.deleteAdminById(adminId, tenantId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
