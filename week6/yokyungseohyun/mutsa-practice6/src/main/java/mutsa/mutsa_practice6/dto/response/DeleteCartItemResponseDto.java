package mutsa.mutsa_practice6.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteCartItemResponseDto {
    private boolean success;
    private String message;
}
