package com.educandoweb.course.entities.enums;

// Enum representando os estados de um pedido
public enum OrderStatus {
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code; // Código associado aos estados do pedido
	
	// Construtor com parâmetros
	private OrderStatus(int code) {
		this.code = code;
	}
	
	// Método de retorno da variável 'code'
	public int getCode() {
		return code;
	}
	
	// Converte um código em um enum OrderStatus
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()){
			if(value.getCode() == code) {
				return value;
			}
		}
		// Exceção lançada caso o código seja inválido
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
