package cn.niya.api.dao;

import cn.niya.api.entity.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends BaseMapper<UserPO> {


}
