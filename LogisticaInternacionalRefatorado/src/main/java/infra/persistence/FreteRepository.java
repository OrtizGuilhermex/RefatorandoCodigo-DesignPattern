package infra.persistence;

import domain.model.Frete;
import infra.config.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SRP: Esta classe tem a única responsabilidade de saber COMO salvar os dados no MySQL.
 * Ela implementa a interface do domínio para manter o desacoplamento.
 */
public class FreteRepository implements domain.strategy.FreteRepository {

    @Override
    public void salvar(Frete frete) {
        String sql = "INSERT INTO envios (peso, valor_base, imposto, tipoTransporte ,total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, frete.getPeso());
            stmt.setDouble(2, frete.getValorFrete());
            stmt.setDouble(3, frete.getImposto());
            stmt.setString(4, frete.getTipoImposto());
            stmt.setDouble(5, frete.getValorFrete() + frete.getImposto());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("[ERRO DB] Falha ao persistir dados: " + e.getMessage());
        }
    }
}