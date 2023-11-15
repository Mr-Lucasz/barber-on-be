create table status(
    statusId serial primary key,
    statusNome varchar(100)
);
create table imagem(
    imagemID serial primary key,
    imagemCaminho varchar(200),
    barbeariaId int references barbearia(barbeariaId),
    barbeiroId int references barbeiro(barbeiroId)
);
create table barbearia(
    barbeariaId serial primary key,
    barbeariaNome varchar(100),
    barbeariaDescricao varchar(1000),
    barbeariaCNPJ varchar(18),
    barbeariaEnderecoCEP varchar(10),
    barbeariaEnderecoCidade varchar(100),
    barbeariaEnderecoRua varchar(100),
    barbeariaEnderecoBairro varchar(100),
    barbeariaEnderecoNumero varchar(10),
    barbeariaMediaAvaliacao numeric(2,1)
);
create table cliente(
    clienteID serial primary key,
    clienteNome varchar(100),
    clienteDataNascimento date,
    clienteCPF varchar(14),
    clienteTelefone varchar(20),
    clienteEmail varchar(100),
    clienteSenha varchar(255)
);
create table cupom(
    cupomID serial primary key,
    cupomQuantidade int,
    cupomPorcentagem numeric(5,2),
    cupomCodigo varchar(40)
);
create table barbeiro(
    barbeiroID serial primary key,
    barbeiroBarbeariaID int references barbearia(barbeariaID),
    barbeiroNome varchar(100),
    barbeiroDataNascimento date,
    barbeiroCPF varchar(14),
    barbeiroTelefone varchar(20),
    barbeiroEmail varchar(100),
    barbeiroSenha varchar(255),
    barbeiroMediaAvaliacao numeric(2,1)
);
create table agenda(
    agendaID serial primary key,
    agendaBarbeiroID int references barbeiro(barbeiroID),
    agendaDiaSemana int,
    agendaHorarioInicio timestamp,
    agendaHorarioFim timestamp
);
create table servico(
    servicoID serial primary key,
    servicoBarbeiroID int references barbeiro(barbeiroID),
    servicoImagemID int references imagem(imagemID),
    servicoTitulo varchar(100),
    servicodescricao varchar(1000),
    servicoTempoMinuto numeric(5,2),
    servicoValor numeric(8,2)
);
create table agendamento(
    agendamentoID serial primary key,
    agendamentoServicoID int references servico(servicoID),
    agendamentoBarbeiroID int references barbeiro(barbeiroID),
    agendamentoClienteID int references cliente(clienteID),
    agendamentoStatusID int references status(statusId),
    agendamentoHorarioInicio timestamp,
    agendamentoHorarioFim timestamp
);
create table avaliacao(
    avaliacaoID serial primary key,
    avaliacaoAgendamentoID int references agendamento(agendamentoID),
    avaliacaoNota int
);
create table pausa(
    pausaID serial primary key,
    pausaAgendaID int references agenda(agendaID),
    pausaHorarioInicio timestamp,
    pausaHorarioFim timestamp
);
create table pagamento(
    pagamentoID serial primary key,
    pagamentoAgendamentoID int references agendamento(agendamentoID),
    pagamentoCupomID int references cupom(cupomID),
    pagamentoForma varchar,
    pagamentoValor numeric(8,2)
);