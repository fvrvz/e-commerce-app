package com.fvrvz.ecommerce.handlers;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
