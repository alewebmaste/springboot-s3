package com.borba.storage.repository;

import com.borba.storage.configuration.AwsProperties;
import com.borba.storage.model.Pedido;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class PedidoRepository {

    private final DynamoDbTable<Pedido> pedidoTable;

    public PedidoRepository(DynamoDbEnhancedClient enhancedClient, AwsProperties props) {
        String tableName = props.getDynamoDb().getTable();
        this.pedidoTable = enhancedClient.table(tableName, TableSchema.fromBean(Pedido.class));
    }

    public void init(){
        // opcional: criar a tabela se necessario
        // pedidoTable.createTable();
    }

    public void salvar(Pedido pedido){
        pedidoTable.putItem(pedido);
    }

    public Pedido buscarPorId(String id){
        return pedidoTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public void deletar(String id){
        pedidoTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
