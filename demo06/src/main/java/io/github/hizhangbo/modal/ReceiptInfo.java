package io.github.hizhangbo.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptInfo {
    private String name;
    private String passport;
    private float money;
    private String receiptNum;
}
