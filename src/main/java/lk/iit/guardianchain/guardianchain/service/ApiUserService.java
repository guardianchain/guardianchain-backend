package lk.iit.guardianchain.guardianchain.service;

import lk.iit.guardianchain.guardianchain.model.ApiUser;
import java.util.Optional;

public interface ApiUserService {
    ApiUser createOrGetUser(String nic, String email);
    Optional<ApiUser> getUserByNic(String nic);
    Optional<ApiUser> getUserByEmail(String email);
} 