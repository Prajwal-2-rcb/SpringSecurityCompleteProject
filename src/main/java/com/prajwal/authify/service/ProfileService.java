package com.prajwal.authify.service;

import com.prajwal.authify.dto.ProfileRequest;
import com.prajwal.authify.dto.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest profileRequest);
}
