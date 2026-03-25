package com.prajwal.authify.service;

import com.prajwal.authify.dto.ProfileRequest;
import com.prajwal.authify.dto.ProfileResponse;
import com.prajwal.authify.entity.UserEntity;
import com.prajwal.authify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        UserEntity newProfile=convertToUserEntity(profileRequest);
        if(!userRepository.existsByEmail(newProfile.getEmail())){
            newProfile=userRepository.save(newProfile);
            return convertToProfileResponse(newProfile);
        }

        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");


    }

    private ProfileResponse convertToProfileResponse(UserEntity newProfile) {
        return ProfileResponse.builder()
                .email(newProfile.getEmail())
                .name(newProfile.getName())
                .userId(newProfile.getUserId())
                .isAccountVerified(newProfile.getAccountVerified())
                .build();
    }

    private UserEntity convertToUserEntity(ProfileRequest profileRequest) {
        return UserEntity.builder()
                .email(profileRequest.getEmail())
                .userId(UUID.randomUUID().toString())
                .name(profileRequest.getName())
                .password(passwordEncoder.encode(profileRequest.getPassword()))
                .accountVerified(false)
                .resetOtpExpireAt(null)
                .resetOtp(null)
                .verifyOtpExpireAt(null)
                .verifyOtp(null)
                .build();

    }
}
