/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultado;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";

        conn = new conectaDAO().connectDB();

        try {

            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.execute();
            prep.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        String sql = "SELECT * FROM produtos";

        conn = new conectaDAO().connectDB();

        try {

            prep = conn.prepareStatement(sql);

            resultado = prep.executeQuery();

            while (resultado.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultado.getInt("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setValor(resultado.getInt("valor"));
                produto.setStatus(resultado.getString("status"));
                listagem.add(produto);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return listagem;
    }
    
    public void venderProduto(){}
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
    
      String sql = "SELECT * FROM produtos p WHERE p.status = 'Vendido'";

        conn = new conectaDAO().connectDB();

        try {

            prep = conn.prepareStatement(sql);

            resultado = prep.executeQuery();

            while (resultado.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultado.getInt("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setValor(resultado.getInt("valor"));
                produto.setStatus(resultado.getString("status"));
                listagem.add(produto);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return listagem;
    }
    
}
