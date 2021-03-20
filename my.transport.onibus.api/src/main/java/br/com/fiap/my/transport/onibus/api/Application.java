package br.com.fiap.my.transport.onibus.api;

import br.com.fiap.my.transport.onibus.api.dto.*;
import br.com.fiap.my.transport.onibus.api.entity.Posicao;
import br.com.fiap.my.transport.onibus.api.service.LinhaService;
import br.com.fiap.my.transport.onibus.api.service.OnibusService;
import br.com.fiap.my.transport.onibus.api.service.PosicaoService;
import br.com.fiap.my.transport.onibus.api.service.RotaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private final LinhaService linhaService;
	private final OnibusService onibusService;
	private final RotaService rotaService;
	private final PosicaoService posicaoService;

	public Application(LinhaService linhaService,
					   OnibusService onibusService,
					   PosicaoService posicaoService,
					   RotaService rotaService){
		this.linhaService = linhaService;
		this.onibusService = onibusService;
		this.posicaoService = posicaoService;
		this.rotaService = rotaService;

		this.createData();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void createData(){
		LinhaCreateUpdateDTO linhaCreate001 = new LinhaCreateUpdateDTO();
		linhaCreate001.setAtivo(true);
		linhaCreate001.setCodigoLinha("1234");

		LinhaDTO linha01 = this.linhaService.create(linhaCreate001);
		this.CreateRota1234(1L);

		OnibusCreateUpdateDTO onibusCreate1234001 = new OnibusCreateUpdateDTO();
		onibusCreate1234001.setAtivo( true );
		onibusCreate1234001.setCodigo("1234-001");
		OnibusDTO onibus1234001 = this.onibusService.create(1L, onibusCreate1234001);

		PosicaoCreateUpdateDTO posicao1234001_01 = new PosicaoCreateUpdateDTO();
		posicao1234001_01.setLatitude("-23.583777");
		posicao1234001_01.setLongitude("-46.611073");
		posicao1234001_01.setLotacaoAtual(20);
		PosicaoDTO posicao123400101 = this.posicaoService.create(1L, posicao1234001_01);

		PosicaoCreateUpdateDTO posicao1234001_02 = new PosicaoCreateUpdateDTO();
		posicao1234001_02.setLatitude("-23.583277");
		posicao1234001_02.setLongitude("-46.613073");
		posicao1234001_02.setLotacaoAtual(18);
		PosicaoDTO posicao123400102 = this.posicaoService.create(1L, posicao1234001_02);

		PosicaoCreateUpdateDTO posicao1234001_03 = new PosicaoCreateUpdateDTO();
		posicao1234001_03.setLatitude("-23.573277");
		posicao1234001_03.setLongitude("-46.600872");
		posicao1234001_03.setLotacaoAtual(18);
		PosicaoDTO posicao123400103 = this.posicaoService.create(1L, posicao1234001_03);

		OnibusCreateUpdateDTO onibusCreate1234002 = new OnibusCreateUpdateDTO();
		onibusCreate1234002.setAtivo( false );
		onibusCreate1234002.setCodigo("1234-002");
		OnibusDTO onibus1234002 = this.onibusService.create(1L, onibusCreate1234002);

		OnibusCreateUpdateDTO onibusCreate1234003 = new OnibusCreateUpdateDTO();
		onibusCreate1234003.setAtivo( true );
		onibusCreate1234003.setCodigo("1234-003");
		OnibusDTO onibus1234003 = this.onibusService.create(1L, onibusCreate1234003);

		LinhaCreateUpdateDTO linhaCreate002 = new LinhaCreateUpdateDTO();
		linhaCreate002.setAtivo(true);
		linhaCreate002.setCodigoLinha("9121");
		LinhaDTO linha02 = this.linhaService.create(linhaCreate002);
		this.CreateRota9121(2L);

		OnibusCreateUpdateDTO onibusCreate9121001 = new OnibusCreateUpdateDTO();
		onibusCreate9121001.setAtivo( false );
		onibusCreate9121001.setCodigo("9121-001");
		OnibusDTO onibus9121001 = this.onibusService.create(2L, onibusCreate9121001);

		OnibusCreateUpdateDTO onibusCreate9121002 = new OnibusCreateUpdateDTO();
		onibusCreate9121002.setAtivo( true );
		onibusCreate9121002.setCodigo("9121-002");
		OnibusDTO onibus9121002 = this.onibusService.create(2L, onibusCreate9121002);

		OnibusCreateUpdateDTO onibusCreate9121003 = new OnibusCreateUpdateDTO();
		onibusCreate9121003.setAtivo( true );
		onibusCreate9121003.setCodigo("9121-003");
		OnibusDTO onibus9121003 = this.onibusService.create(2L, onibusCreate9121003);
	}

	public void CreateRota1234(Long idLinha){
		RotaCreateUpdateDTO rota001 = new RotaCreateUpdateDTO();

		rota001.setOrdem(1);
		rota001.setLatitude("-23.583777");
		rota001.setLongitude("-46.611073");
		rota001.setAtivo(true);
		this.rotaService.create(idLinha, rota001);

		RotaCreateUpdateDTO rota002 = new RotaCreateUpdateDTO();

		rota002.setOrdem(2);
		rota002.setLatitude("-23.588442");
		rota002.setLongitude("-46.610605");
		rota002.setAtivo(true);
		this.rotaService.create(idLinha, rota002);

		RotaCreateUpdateDTO rota003 = new RotaCreateUpdateDTO();

		rota003.setOrdem(3);
		rota003.setLatitude("-23.588374");
		rota003.setLongitude("-46.608311");
		rota003.setAtivo(true);
		this.rotaService.create(idLinha, rota003);

		RotaCreateUpdateDTO rota004 = new RotaCreateUpdateDTO();

		rota004.setOrdem(4);
		rota004.setLatitude("-23.584802");
		rota004.setLongitude("-46.608687");
		rota004.setAtivo(true);
		this.rotaService.create(idLinha, rota004);

		RotaCreateUpdateDTO rota005 = new RotaCreateUpdateDTO();

		rota005.setOrdem(5);
		rota005.setLatitude("-23.584674");
		rota005.setLongitude("-46.607617");
		rota005.setAtivo(true);
		this.rotaService.create(idLinha, rota005);

		RotaCreateUpdateDTO rota006 = new RotaCreateUpdateDTO();

		rota006.setOrdem(6);
		rota006.setLatitude("-23.583502");
		rota006.setLongitude("-46.607773");
		rota006.setAtivo(true);
		this.rotaService.create(idLinha, rota006);
	}

	public void CreateRota9121(Long idLinha){
		RotaCreateUpdateDTO rota001 = new RotaCreateUpdateDTO();

		rota001.setOrdem(1);
		rota001.setLatitude("-23.535487");
		rota001.setLongitude("-46.635460");
		rota001.setAtivo(true);
		this.rotaService.create(idLinha, rota001);

		RotaCreateUpdateDTO rota002 = new RotaCreateUpdateDTO();

		rota002.setOrdem(2);
		rota002.setLatitude("-23.537867");
		rota002.setLongitude("-46.635977");
		rota002.setAtivo(true);
		this.rotaService.create(idLinha, rota002);

		RotaCreateUpdateDTO rota003 = new RotaCreateUpdateDTO();

		rota003.setOrdem(3);
		rota003.setLatitude("-23.537828");
		rota003.setLongitude("-46.632984");
		rota003.setAtivo(true);
		this.rotaService.create(idLinha, rota003);

		RotaCreateUpdateDTO rota004 = new RotaCreateUpdateDTO();

		rota004.setOrdem(4);
		rota004.setLatitude("-23.538364");
		rota004.setLongitude("-46.630629");
		rota004.setAtivo(true);
		this.rotaService.create(idLinha, rota004);

		RotaCreateUpdateDTO rota005 = new RotaCreateUpdateDTO();

		rota005.setOrdem(5);
		rota005.setLatitude("-23.539766");
		rota005.setLongitude("-46.630720");
		rota005.setAtivo(true);
		this.rotaService.create(idLinha, rota005);

		RotaCreateUpdateDTO rota006 = new RotaCreateUpdateDTO();

		rota006.setOrdem(6);
		rota006.setLatitude("-23.539274");
		rota006.setLongitude("-46.632903");
		rota006.setAtivo(true);
		this.rotaService.create(idLinha, rota006);

		RotaCreateUpdateDTO rota007 = new RotaCreateUpdateDTO();

		rota007.setOrdem(7);
		rota007.setLatitude("-23.539097");
		rota007.setLongitude("-46.634974");
		rota007.setAtivo(true);
		this.rotaService.create(idLinha, rota007);

		RotaCreateUpdateDTO rota008 = new RotaCreateUpdateDTO();

		rota008.setOrdem(8);
		rota008.setLatitude("-23.538640");
		rota008.setLongitude("-46.636626");
		rota008.setAtivo(true);
		this.rotaService.create(idLinha, rota008);

		RotaCreateUpdateDTO rota009 = new RotaCreateUpdateDTO();

		rota009.setOrdem(9);
		rota009.setLatitude("-23.537878");
		rota009.setLongitude("-46.636985");
		rota009.setAtivo(true);
		this.rotaService.create(idLinha, rota009);

		RotaCreateUpdateDTO rota010 = new RotaCreateUpdateDTO();

		rota010.setOrdem(10);
		rota010.setLatitude("-23.535104");
		rota010.setLongitude("-46.636974");
		rota010.setAtivo(true);
		this.rotaService.create(idLinha, rota010);
	}
}
