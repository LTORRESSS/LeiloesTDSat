import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;

    public boolean cadastrarProduto(ProdutosDTO objproduto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try (Connection conn = new ConexaoDAO().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, objproduto.getNome());
            pstm.setInt(2, objproduto.getValor());
            pstm.setString(3, objproduto.getStatus());

            pstm.execute();
            return true;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ProdutosDAO: " + erro.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        try (Connection conn = new ConexaoDAO().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));         // Se existir coluna id no banco
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ProdutosDAO listar: " + erro.getMessage());
        }

        return lista;
    }

}
