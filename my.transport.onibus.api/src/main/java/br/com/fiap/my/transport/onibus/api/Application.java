package br.com.fiap.my.transport.onibus.api;

import br.com.fiap.my.transport.onibus.api.dto.*;
import br.com.fiap.my.transport.onibus.api.entity.Linha;
import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import br.com.fiap.my.transport.onibus.api.entity.Posicao;
import br.com.fiap.my.transport.onibus.api.entity.Rota;
import br.com.fiap.my.transport.onibus.api.repository.LinhaRepository;
import br.com.fiap.my.transport.onibus.api.repository.OnibusRepository;
import br.com.fiap.my.transport.onibus.api.repository.PosicaoRepository;
import br.com.fiap.my.transport.onibus.api.repository.RotaRepository;
import br.com.fiap.my.transport.onibus.api.service.LinhaService;
import br.com.fiap.my.transport.onibus.api.service.OnibusService;
import br.com.fiap.my.transport.onibus.api.service.PosicaoService;
import br.com.fiap.my.transport.onibus.api.service.RotaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {/*
	private final LinhaService linhaService;
	private final OnibusService onibusService;
	private final RotaService rotaService;
	private final PosicaoService posicaoService;
	*/
	private final LinhaRepository linhaRepository;
	private final OnibusRepository onibusRepository;
	private final PosicaoRepository posicaoRepository;
	private final RotaRepository rotaRepository;

	public Application(	LinhaRepository linhaRepository,
			OnibusRepository onibusRepository,
			PosicaoRepository posicaoRepository,
			RotaRepository rotaRepository){
		this.linhaRepository = linhaRepository;
		this.onibusRepository = onibusRepository;
		this.posicaoRepository = posicaoRepository;
		this.rotaRepository = rotaRepository;

		this.createData();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void createData(){
		Linha linhaCreate001 = new Linha();
		linhaCreate001.setAtivo(true);
		linhaCreate001.setCodigo("1234");

		Linha linha01 = this.linhaRepository.save(linhaCreate001);
		this.CreateRota1234(linha01);

		Onibus onibusCreate1234001 = new Onibus(linha01);
		onibusCreate1234001.setAtivo( true );
		onibusCreate1234001.setCodigo("1234-001");

		Onibus onibusCreate1234002 = new Onibus(linha01);
		onibusCreate1234002.setAtivo( false );
		onibusCreate1234002.setCodigo("1234-002");

		Onibus onibusCreate1234003 = new Onibus(linha01);
		onibusCreate1234003.setAtivo( true );
		onibusCreate1234003.setCodigo("1234-003");

		List<Onibus> lsOnibus1234 = new ArrayList<>();

		lsOnibus1234.add(onibusCreate1234001);
		lsOnibus1234.add(onibusCreate1234002);
		lsOnibus1234.add(onibusCreate1234003);

		List<Onibus> onibus1234 = this.onibusRepository.saveAll(lsOnibus1234);

		onibusCreate1234001 = onibus1234.get(0);
		onibusCreate1234002 = onibus1234.get(1);
		onibusCreate1234003 = onibus1234.get(2);

		Posicao posicao1234001_01 = new Posicao(onibusCreate1234001);
		posicao1234001_01.setLatitude("-23.583777");
		posicao1234001_01.setLongitude("-46.611073");
		posicao1234001_01.setLotacaoAtual(20);

		Posicao posicao1234001_02 = new Posicao(onibusCreate1234001);
		posicao1234001_02.setLatitude("-23.583277");
		posicao1234001_02.setLongitude("-46.613073");
		posicao1234001_02.setLotacaoAtual(18);

		Posicao posicao1234001_03 = new Posicao(onibusCreate1234001);
		posicao1234001_03.setLatitude("-23.573277");
		posicao1234001_03.setLongitude("-46.600872");
		posicao1234001_03.setLotacaoAtual(18);


		List<Posicao> lsPosicao1234001 =  new ArrayList<>();
		lsPosicao1234001.add(posicao1234001_01);
		lsPosicao1234001.add(posicao1234001_02);
		lsPosicao1234001.add(posicao1234001_03);

		List<Posicao> posicao1234 = this.posicaoRepository.saveAll(lsPosicao1234001);
		posicao1234001_01 = posicao1234.get(0);
		posicao1234001_02 = posicao1234.get(1);
		posicao1234001_03 = posicao1234.get(2);

		Linha linhaCreate002 = new Linha();
		linhaCreate002.setAtivo(true);
		linhaCreate002.setCodigo("9121");
		Linha linha02 = this.linhaRepository.save(linhaCreate002);

		this.CreateRota9121(linha02);

		Onibus onibusCreate9121001 = new Onibus(linha02);
		onibusCreate9121001.setAtivo( false );
		onibusCreate9121001.setCodigo("9121-001");


		Onibus onibusCreate9121002 = new Onibus(linha02);
		onibusCreate9121002.setAtivo( true );
		onibusCreate9121002.setCodigo("9121-002");

		Onibus onibusCreate9121003 = new Onibus(linha02);
		onibusCreate9121003.setAtivo( true );
		onibusCreate9121003.setCodigo("9121-003");

		List<Onibus> lsOnibus9121 = new ArrayList<>();
		lsOnibus9121.add(onibusCreate9121001);
		lsOnibus9121.add(onibusCreate9121002);
		lsOnibus9121.add(onibusCreate9121003);

		this.onibusRepository.saveAll(lsOnibus9121);
	}

	public void CreateRota1234(Linha linha){
		List<Rota> lsRota = new ArrayList<>();

		Rota rota001 = new Rota(linha);
		rota001.setOrdem(1);
		rota001.setLatitude("-23.583777");
		rota001.setLongitude("-46.611073");
		rota001.setAtivo(true);
		lsRota.add(rota001);

		Rota rota002 = new Rota(linha);
		rota002.setOrdem(2);
		rota002.setLatitude("-23.588442");
		rota002.setLongitude("-46.610605");
		rota002.setAtivo(true);
		lsRota.add(rota002);

		Rota rota003 = new Rota(linha);
		rota003.setOrdem(3);
		rota003.setLatitude("-23.588374");
		rota003.setLongitude("-46.608311");
		rota003.setAtivo(true);
		lsRota.add(rota003);

		Rota rota004 = new Rota(linha);
		rota004.setOrdem(4);
		rota004.setLatitude("-23.584802");
		rota004.setLongitude("-46.608687");
		rota004.setAtivo(true);
		lsRota.add(rota004);

		Rota rota005 = new Rota(linha);
		rota005.setOrdem(5);
		rota005.setLatitude("-23.584674");
		rota005.setLongitude("-46.607617");
		rota005.setAtivo(true);
		lsRota.add(rota005);

		Rota rota006 = new Rota(linha);
		rota006.setOrdem(6);
		rota006.setLatitude("-23.583502");
		rota006.setLongitude("-46.607773");
		rota006.setAtivo(true);
		lsRota.add(rota006);

		this.rotaRepository.saveAll(lsRota);
	}

	public void CreateRota9121(Linha linha){
		List<Rota> lsRota = new ArrayList<>();

		Rota rota001 = new Rota(linha);
		rota001.setOrdem(1);
		rota001.setLatitude("-23.535487");
		rota001.setLongitude("-46.635460");
		rota001.setAtivo(true);
		lsRota.add(rota001);

		Rota rota002 = new Rota(linha);
		rota002.setOrdem(2);
		rota002.setLatitude("-23.537867");
		rota002.setLongitude("-46.635977");
		rota002.setAtivo(true);
		lsRota.add(rota002);

		Rota rota003 = new Rota(linha);
		rota003.setOrdem(3);
		rota003.setLatitude("-23.537828");
		rota003.setLongitude("-46.632984");
		rota003.setAtivo(true);
		lsRota.add(rota003);

		Rota rota004 = new Rota(linha);
		rota004.setOrdem(4);
		rota004.setLatitude("-23.538364");
		rota004.setLongitude("-46.630629");
		rota004.setAtivo(true);
		lsRota.add(rota004);

		Rota rota005 = new Rota(linha);
		rota005.setOrdem(5);
		rota005.setLatitude("-23.539766");
		rota005.setLongitude("-46.630720");
		rota005.setAtivo(true);
		lsRota.add(rota005);

		Rota rota006 = new Rota(linha);
		rota006.setOrdem(6);
		rota006.setLatitude("-23.539274");
		rota006.setLongitude("-46.632903");
		rota006.setAtivo(true);
		lsRota.add(rota006);

		Rota rota007 = new Rota(linha);
		rota007.setOrdem(7);
		rota007.setLatitude("-23.539097");
		rota007.setLongitude("-46.634974");
		rota007.setAtivo(true);
		lsRota.add(rota007);

		Rota rota008 = new Rota(linha);
		rota008.setOrdem(8);
		rota008.setLatitude("-23.538640");
		rota008.setLongitude("-46.636626");
		rota008.setAtivo(true);
		lsRota.add(rota008);

		Rota rota009 = new Rota(linha);
		rota009.setOrdem(9);
		rota009.setLatitude("-23.537878");
		rota009.setLongitude("-46.636985");
		rota009.setAtivo(true);
		lsRota.add(rota008);

		Rota rota010 = new Rota(linha);
		rota010.setOrdem(10);
		rota010.setLatitude("-23.535104");
		rota010.setLongitude("-46.636974");
		rota010.setAtivo(true);
		lsRota.add(rota008);

		this.rotaRepository.saveAll(lsRota);
	}
}
