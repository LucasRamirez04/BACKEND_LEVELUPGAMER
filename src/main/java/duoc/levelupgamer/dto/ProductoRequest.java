package duoc.levelupgamer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductoRequest {

    @NotNull(message = "La categoria es obligatoria")
    private Integer id_categoria;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre_producto;

    @NotBlank(message = "La descripcion del producto es obligatoria")
    private String descripcion_producto;

    @NotNull(message = "El precio del producto es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private Integer precio_producto;

    @NotNull(message = "El porcentaje del producto es obligatorio")
    @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
    @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
    private Integer porcentaje_producto;

    @NotNull(message = "El stock del producto es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock_producto;

    @NotBlank(message = "La imagen del producto es obligatoria")
    private String imagen_producto;
}