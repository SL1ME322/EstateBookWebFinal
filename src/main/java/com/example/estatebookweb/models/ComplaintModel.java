package com.example.estatebookweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Complaint")
public class ComplaintModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Complaint")
    private Long id;

    @ElementCollection(targetClass = UserComplaintEnum.class,  fetch = FetchType.EAGER)
    @CollectionTable(name = "Complaint_Type", joinColumns = @JoinColumn(name = "complaint_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "NVARCHAR(255)")
    private Set<UserComplaintEnum> type;

    @Column(name = "Complaint_Description",  columnDefinition = "NVARCHAR(255)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "Complant_Sender_ID")
    private UserModel sender;

    @ManyToOne
    @JoinColumn(name = "Complant_Recipient_ID")
    private UserModel recipient;

    @ManyToOne
    @JoinColumn(name = "Complant_Moderator_ID")
    private UserModel moderator;


    @Column(name = "Conclusion")
    private String conclusion;

    @ElementCollection(targetClass = BanTypeEnum.class,  fetch = FetchType.EAGER)
    @CollectionTable(name = "Ban_Type", joinColumns = @JoinColumn(name = "complaint_id"))
    @Enumerated(EnumType.STRING)
    private Set<BanTypeEnum> banType;
}
