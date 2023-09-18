package cn.p2nn.meteor.modules.client.service;

import cn.hutool.core.util.RandomUtil;
import cn.p2nn.meteor.constants.CommonConstant;
import cn.p2nn.meteor.modules.client.entity.CtMember;
import cn.p2nn.meteor.modules.client.mapper.CtMemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CtMemberService extends ServiceImpl<CtMemberMapper, CtMember> {

    public CtMember getByAccountId(String accountId) {
        return this.lambdaQuery().eq(CtMember::getAccountId, accountId).one();
    }

    @Transactional(rollbackFor = Exception.class)
    public void createWx(String accountId) {
        CtMember member = new CtMember();
        member.setAccountId(accountId).setNickname(CommonConstant.WX_NICKNAME_PREFIX + RandomUtil.randomString(24));
        this.save(member);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(CtMember member) {
        this.updateById(member);
    }

}