package com.ejtrio.todolistapi.infrastructure.models.projections;

import com.ejtrio.todolistapi.infrastructure.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userDetail", types = {User.class})
public interface UserDetail {

    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();

    String getEmail();
}
