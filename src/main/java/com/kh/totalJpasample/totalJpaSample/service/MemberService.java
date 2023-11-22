package com.kh.totalJpasample.totalJpaSample.service;

import com.kh.totalJpasample.totalJpaSample.dto.MemberDto;
import com.kh.totalJpasample.totalJpaSample.entity.Member;
import com.kh.totalJpasample.totalJpaSample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service  // 해당 객체를 빈으로 등록
@RequiredArgsConstructor  // 매개변수가 전부 포함된 생성자를 자동으로 생성 해줌
public class MemberService {
    private final MemberRepository memberRepository;
    // 회원 등록
    public boolean saveMember(MemberDto memberDto) {
        // 이미 등록된 회원인지 확인 하는 쿼리문
        boolean isReg = memberRepository.existsByEmail(memberDto.getEmail());
        if(isReg) return false;

        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        memberRepository.save(member);
        return true;
    }

    // 회원 전체 조회
    public List<MemberDto> getMemberList() {
        List<MemberDto> memberDtos = new ArrayList<>();
        List<Member> members = memberRepository.findAll();
        for(Member member : members) {
            memberDtos.add(convertEntityTODto(member));
        }
        return memberDtos;
    }

    // 페이지 네이션 조회
    public List<MemberDto> getMemberList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<MemberDto> memberDtos = new ArrayList<>();
        List<Member> members = memberRepository.findAll(pageable).getContent();
        for(Member member : members) {
            memberDtos.add(convertEntityTODto(member));
        }
        return memberDtos;
    }

    // 페이지 수 조회
    public int getMemberPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return memberRepository.findAll(pageable).getTotalPages();
    }


    // 회원 상세 조회
    public MemberDto getMemberDetail(String email) {
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(
                            () -> new RuntimeException("해당 회원이 존재하지 않습니다. ")
                    );
            return convertEntityTODto(member);
    }




    // 회원 엔티티를 DTO로 변환 하는 메소드 만들기
    private MemberDto convertEntityTODto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(member.getEmail());
        memberDto.setPassword(member.getPassword());
        memberDto.setName(member.getName());
        memberDto.setRegDate(member.getRegDate());
        return memberDto;
    }

}
