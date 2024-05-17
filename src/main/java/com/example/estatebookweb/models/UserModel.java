    package com.example.estatebookweb.models;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;
    import java.util.Date;
    import java.util.Set;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "Users")
    public class UserModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_User")
        private Long id;

        @Column(name = "Name",    columnDefinition = "NVARCHAR(255)"  )
        private String name;

        @Column(name = "Surname",  columnDefinition = "NVARCHAR(255)" )
        private String surname;

        @Column(name = "Middle_Name",    columnDefinition = "NVARCHAR(255)")
        private String middleName;

        //@Column(name = "Login", nullable = false, unique = true)
        @Column(name = "Login")
        private String login;

        @Column(name = "Password", nullable = false)
        private String password;

        @Column(name = "Avatar", columnDefinition = "varchar(max) default 'default_avatar.jpg'")
        private String avatar;

        @Column(name = "Phone",     columnDefinition = "NVARCHAR(255)" )
        private String phone;

        @Column(name = "Location", columnDefinition = "NVARCHAR(255)")
        private String location;

        @Column(name = "Description", columnDefinition = "NVARCHAR(255)")
        private String description;

        @Column(name = "Average_Mark" )
        private Float averageMark;

        @Column(name = "Registration_Date"   )
        private Date registrationDate;

        @ElementCollection(targetClass = RoleEnum.class,  fetch = FetchType.EAGER)
        @CollectionTable(name = "ROLE", joinColumns = @JoinColumn(name = "users_id"))
        @Enumerated(EnumType.STRING)
        private Set<RoleEnum> roles;

        @ElementCollection(targetClass = StatusEnum.class,  fetch = FetchType.EAGER)
        @CollectionTable(name = "STATUS", joinColumns = @JoinColumn(name = "users_id"))
        @Enumerated(EnumType.STRING)
        private  Set<StatusEnum> status;

        @Column
        private Boolean isBanned;
        @Column
        private LocalDateTime bannedUntilDate;

        @Column
        private String complaintAmounts;


    }
