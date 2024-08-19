package com.example.myLibrary;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class MemberManager {
    private Set<MemberRecord> memberSet;

    public MemberManager() {
        this.memberSet = new HashSet<>();
    }

    public void addMember(MemberRecord member) {
        memberSet.add(member);
    }

    public MemberRecord getMemberById(String memberId) {
        return memberSet.stream()
                .filter(member -> member.getMemberId().equals(memberId))
                .findFirst()
                .orElse(null);
    }
    public Set<MemberRecord> getAllMembers(){
        return Collections.unmodifiableSet(memberSet);
    }
}