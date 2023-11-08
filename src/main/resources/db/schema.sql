
    create table atendentes (
        id_atendente bigint generated by default as identity,
        no_atendente varchar(255),
        atendente_id bigint,
        primary key (id_atendente)
    );

    create table atendimentos (
        id_atendimento bigint generated by default as identity,
        en_atendimento_assunto varchar(255) check (en_atendimento_assunto in ('CARTAO','EMPRESTIMO','OUTROS')),
        tx_descricao varchar(4000),
        atendimento_id bigint,
        primary key (id_atendimento)
    );

    create table demandas (
        id_demanda bigint generated by default as identity,
        en_atendimento_status varchar(255) check (en_atendimento_status in ('CONCLUIDO','PENDENTE','EM_EXECUCAO')),
        atendimento_id bigint,
        team_id bigint,
        primary key (id_demanda)
    );

    create table times (
        id_time bigint generated by default as identity,
        en_time_setor varchar(255) check (en_time_setor in ('CARTAO','EMPRESTIMO','OUTROS')),
        primary key (id_time)
    );

    alter table if exists demandas 
       drop constraint if exists UK_a9lwp53edvhlhr98isgnuh4t6;

    alter table if exists demandas 
       add constraint UK_a9lwp53edvhlhr98isgnuh4t6 unique (atendimento_id);

    alter table if exists demandas 
       drop constraint if exists UK_mddymgibe6tw9j3mdx2b6xk32;

    alter table if exists demandas 
       add constraint UK_mddymgibe6tw9j3mdx2b6xk32 unique (team_id);

    alter table if exists atendentes 
       add constraint FKn1p1t7l7vne1ah2kxsr5sauj8 
       foreign key (atendente_id) 
       references times;

    alter table if exists atendimentos 
       add constraint FKgyy0ru7rkrvqn1gdor2tp4j7x 
       foreign key (atendimento_id) 
       references atendentes;

    alter table if exists demandas 
       add constraint FKes90xar2ntafjfv1aoscfx8er 
       foreign key (atendimento_id) 
       references atendimentos;

    alter table if exists demandas 
       add constraint FKgsmbpvna5djbxaisb71feg80y 
       foreign key (team_id) 
       references times;