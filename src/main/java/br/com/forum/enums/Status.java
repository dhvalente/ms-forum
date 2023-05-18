package br.com.forum.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Status {
    PLACED("Realizado" , 221),
    CANCELED("Cancelado" , 222),
    PAID("Pago" , 223),
    NOT_AUTHORIZED("Não autorizado" , 224),
    CONFIRMED ("Confirmado" ,225),
    READY("Pronto" , 226),
    OUT_OF_DELIVERY("Saiu para entrega", 227),
    CREATED ("Pagamento criado" , 228),
    DELIVERED ("Entregue" , 229);

    private final String description;
    private final Integer code;

    private Status(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }

    private static final Map<String, Status> Lookup = new HashMap<>();

    static {
        for (Status keyValue : EnumSet.allOf(Status.class))
            Lookup.put(keyValue.getDescription(), keyValue);
    }

    public static Status get(String description) {
        return Lookup.get(description);
    }

    public static Status getByPaymentMethodId(Integer codeId) {
        for (Status keyValue : EnumSet.allOf(Status.class))
            if (keyValue.getCode().equals(codeId))
                return keyValue;
        return null;
    }
}