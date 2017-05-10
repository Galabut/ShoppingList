package com.shopping.model;

import javax.persistence.*;

@Entity
@Table(name = "invitations")
public class Invitation {

    @Id
    @Column(name = "invitation_id")
    private String invitationId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;


    public Invitation() {
    }

    public String getInvitationId() {
        return invitationId;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public User getSender() {
        return sender;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }
}
