package commune.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(of={"codeInsee", "nomStandard", "codePostal"})
@EqualsAndHashCode(of={"codeInsee"})
public class CommuneL {

    private String codeInsee;
    private String nomStandard;
    private String nomSansPronom;
    private String codePostal;
    private String depCode;
    private int population;
    private float superficieKm2;

    @Builder.Default
    private float superficieHectare = Float.NaN;

}
