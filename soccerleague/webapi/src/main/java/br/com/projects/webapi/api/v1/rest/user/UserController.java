package br.com.projects.webapi.api.v1.rest.user;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.business.publico.authservice.api.AuthService;
import br.com.projects.domain.business.publico.user.DUser;
import br.com.projects.domain.business.publico.user.api.UserService;
import br.com.projects.domain.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Tag(name = "User")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService service, AuthService authService, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param columns represents the table columns to be searched
     * @param operations represent whether the comparisons will be equality, greater than, less than, or not equal
     * @param values represent the values to be used as search parameters
     * @param page pagination attribute, represents the page number of the search
     * @param pageSize pagination attribute, represents the total number of elements on a page
     * @param sort pagination attribute, represents the sorting order
     */
    @Operation(summary = "Search all Users by columns, operations, and values", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(
                    example = """
                    {
                        "numberOfElements": 150,
                        "page": 0,
                        "totalPages": 15,
                        "size": 10,
                        "first": true,
                        "last": false,
                        "sortedBy": "id;d",
                         "content": []
                    }
                    """))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(value = "columns") List<String> columns,
            @RequestParam(value = "operations", required = false, defaultValue = "=") List<String> operations,
            @RequestParam(value = "values", required = false) List<String> values,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort") List<String> sort) {

        if (values == null || values.isEmpty()) {
            values = List.of("");
        }

        return ResponseEntity.ok(
                service.find(PageableRequest.builder()
                        .page(page)
                        .pageSize(pageSize)
                        .sort(sort.toArray(String[]::new))
                        .colunas(columns)
                        .operacoes(operations)
                        .valores(values)
                        .columnMap(Map.of(
                                "id", "id",
                                "name", "name",
                                "password", "password",
                                "email", "email"))
                        .build())
        );
    }

    /**
     * @param id represents the ID of the User to be searched
     */
    @Operation(summary = "Search a User by id", method = "GET", description = "Search for an object by id, regardless of its status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ANALYST', 'ROLE_OPERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> searchById(@PathVariable("id") Integer id){
        authService.validateSelfOrAdmin(Long.valueOf(id));
        return ResponseEntity.ok(service.find(id));
    }

    /**
     * @param email represents the email of the User to be searched
     */
    @Operation(summary = "Search a User by email", method = "GET", description = "Search for an object by email, regardless of its status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ANALYST', 'ROLE_OPERATOR')")
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<?> searchByEmail(@PathVariable("email") String email){
        DUser user = service.find(email);
        authService.validateSelfOrAdmin(Long.valueOf(user.getId()));
        return ResponseEntity.ok(user);
    }

    /**
     * @param dto represents the User object to be created
     */
    @Operation(summary = "Insert a new User", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "409", description = "Conflict"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody
            @Schema(
                    description = "User object for creation",
                    requiredProperties = "name, email, password",
                    example = """
                    {
                        "name": "Name",
                        "password": "12345",
                        "email": "email@email.com"
                    }
                    """
            )
            DUser dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.incluir(dto));
    }

    /**
     * @param dto represents the User object to be updated
     */
    @Operation(summary = "Update a User", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "409", description = "Conflict"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ANALYST', 'ROLE_OPERATOR')")
    @PutMapping
    public ResponseEntity<?> update(
            @RequestBody
            @Schema(
                    description = "User object for editing",
                    requiredProperties = "id, name, password, email",
                    example = """
                    {
                        "id": 1,
                        "name": "Name",
                        "password": "12345",
                        "email": "email@email.com"
                    }
                    """
            )
            DUser dto){
        authService.validateSelfOrAdmin(Long.valueOf(dto.getId()));
        if (!dto.getPassword().startsWith("$2a$")) {  // BCrypt hashes start with $2a$
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return ResponseEntity.ok(service.update(dto));
    }

    /**
     * @param newPassword represents the new password the user has chosen
     * @param oldPassword represents the old password the user had
     */
    @Operation(summary = "Update the logged-in user's password", method = "PATCH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ANALYST', 'ROLE_OPERATOR')")
    @PatchMapping(value = "/password")
    public ResponseEntity<?> updatePassword(@RequestParam("newPassword") String newPassword, @RequestParam("oldPassword") String oldPassword){
        DUser me = authService.authenticated();
        if (!passwordEncoder.matches(oldPassword, me.getPassword())) {
            throw new ValidationException("Incorrect old password");
        }
        service.updatePassword(passwordEncoder.encode(newPassword), oldPassword);
        return ResponseEntity.ok().build();
    }

    /**
     * @param codes list of Users codes to be deleted
     */
    @Operation(summary = "Delete a list of Users", method = "DELETE", description = "The status of the objects is changed to 'Trash'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "206", description = "Partial content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestParam("id") List<Integer> codes){
        List<Integer> successfullyDeleted = new ArrayList<>(), failures = new ArrayList<>();

        codes.forEach(code -> {
            try {
                service.delete(code);
                successfullyDeleted.add(code);
            } catch (Exception e) {
                failures.add(code);
            }
        });

        Map<String, List<Integer>> response = Map.of(
                "successfullyDeleted", successfullyDeleted,
                "failures", failures
        );

        return failures.isEmpty() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response);
    }
}
