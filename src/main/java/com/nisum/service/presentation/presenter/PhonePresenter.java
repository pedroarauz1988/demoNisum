package com.nisum.service.presentation.presenter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhonePresenter {

    private UUID id;
    private String number;
    private String cityCode;
    private String countryCode;
    private UUID userId;
}
