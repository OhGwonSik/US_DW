package com.logistics.om.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseCancelDTO {

	private List<PurchaseDTO> purchaseList;
	private List<CommonDTO> reasonList;
}
