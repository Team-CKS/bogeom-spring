package cks.bogeom.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ItemRequest {
//    @Getter
//    @Setter
//    public static class EmailCheckDTO {
//        @NotEmpty
//        @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
//        private String email;
//    }

    @Getter
    @Setter
    public static class ImageDTO {
        private String image;
        private Gps gps;

        @Getter
        @Setter
        public static class Gps {
            private double latitude;
            private double longitude;

            public Gps(double latitude, double longitude) {
                this.latitude = latitude;
                this.longitude = longitude;
            }
        }

    }


}
