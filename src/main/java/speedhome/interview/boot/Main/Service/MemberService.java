package speedhome.interview.boot.Main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import speedhome.interview.boot.Main.Entity.Book;
import speedhome.interview.boot.Main.Entity.Member;
import speedhome.interview.boot.Main.Repository.MemberRepository;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member addMember(Member member) {
         return memberRepository.save(member);
    }

    public Member updateMember(Long memberId, Member member) {
         Member existingMember = memberRepository.findById(memberId).orElse(null);
        if (existingMember != null) {

            existingMember.setFirstName(member.getFirstName());
            existingMember.setLastName(member.getLastName());
            existingMember.setEmail(member.getEmail());
            existingMember.setUsername(member.getUsername());
            existingMember.setPassword(member.getPassword());

            return memberRepository.save(existingMember);
        }
        return null;
    }
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    public List<Member>  getAllMembers() {
        return memberRepository.findAll();
    }
    public boolean deleteMember(Long memberId) {
        try {
            memberRepository.deleteById(memberId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean existsByUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

 }
