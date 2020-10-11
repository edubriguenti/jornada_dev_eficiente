package com.jornadadev.yfood.pagamentoonline.gateway;

import com.jornadadev.yfood.entities.Pagamento;
import com.jornadadev.yfood.entities.Transacao;
import com.jornadadev.yfood.pagamentoonline.RequestsGateways;
import com.jornadadev.yfood.pagamentoonline.Resultado;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;

@Service
public class GatewaySaori extends Gateway {

//	private String id = "gateway-saori";
//
//	private RequestsGateways requestsGateways;
//
//	public GatewaySaori(RequestsGateways requestsGateways) {
//		super();
//		this.requestsGateways = requestsGateways;
//	}
//
//	@Override
//	public boolean aceiteEspecifico(
//			@NotNull @Valid Pagamento novoPagamentoSalvo) {
//		FormaPagamento formaPagamento = novoPagamentoSalvo.getFormaPagamento();
//		return formaPagamento.pertence(FormaPagamento.visa,
//				FormaPagamento.master);
//	}
//
//	@Override
//	public Resultado<Exception, Transacao> processaEspecifico(
//			@NotNull @Valid Pagamento pagamento) {
//
//		log.debug("Processando pagamento por gateway Saori");
//		DadosCompraGenerico request = new DadosCompraGenerico(pagamento);
//		requestsGateways.saoriProcessa(request);
//		return Resultado.sucesso(Transacao.concluida(this));
//	}
//
//	@Override
//	public BigDecimal custoEspecifico(Pagamento pagamento) {
//		return pagamento.getValor().multiply(new BigDecimal("0.05"));
//	}
//
//	@Override
//	public String toString() {
//		return "GatewaySaori []";
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		GatewaySaori other = (GatewaySaori) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}

}