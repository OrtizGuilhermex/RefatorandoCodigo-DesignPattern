package infra.persistence;

import domain.model.Frete;
import infra.config.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FreteRepository implements domain.strategy.FreteRepository {

    @Override
    public void salvar(Frete frete) {
        String sql = "INSERT INTO fretes (peso, valor_base, imposto, total) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, frete.getPeso());
            stmt.setDouble(2, frete.getValorFrete());
            stmt.setDouble(3, frete.getImposto());
            stmt.setDouble(4, frete.getValorFrete() + frete.getImposto());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("[ERRO DB] Falha ao persistir dados: " + e.getMessage());
        }
    }
}