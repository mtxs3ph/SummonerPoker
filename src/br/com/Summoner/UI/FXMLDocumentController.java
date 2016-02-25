package br.com.Summoner.UI;

import br.com.Summoner.core.Configuracoes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.nio.file.Paths;
import br.com.Summoner.core.Partida;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

public class FXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox deck1;
    
    @FXML
    private ComboBox deck2;
    
    @FXML
    private ComboBox deck3;
    
    @FXML
    private ComboBox deck4;
    
    
    @FXML
    private ComboBox deck5;
    
    
    @FXML
    private ComboBox deck6;
    
    @FXML
    private ComboBox deck7;
    
    @FXML
    private ComboBox deck8;
    
    
    
    
    @FXML
    private TextField txtPathMonsters;

    @FXML
    private CheckBox chkComportamentoFixo;

//    @FXML
//    private TextField txtSetBonusPath;

    @FXML
    private TextField txtNumPlayers;

    @FXML
    private TextField txtPathRelatorio;

    @FXML
    private TextField txtNumTurns;

    @FXML
    private TextField txtQntItensReve;

    @FXML
    private TextField txtQntItensComprados;
    
    @FXML
    private TextField txtPathItems;

    @FXML
    void handleButtonAction(ActionEvent event) throws Exception {
        try
        {
            Configuracoes.NumeroDeJogadores = Integer.parseInt(txtNumPlayers.getText());
            Configuracoes.NumeroDeTurnos = Integer.parseInt(txtNumTurns.getText());
            Configuracoes.QuantidadeItensReveladosPorTurno = Integer.parseInt(txtQntItensReve.getText());
            Configuracoes.QuantidadeItensCompradosPorJogador = Integer.parseInt(txtQntItensComprados.getText());
            
            Configuracoes.CaminhoCSVMonstros = txtPathMonsters.getText();
            Configuracoes.CaminhoCSVItens = txtPathItems.getText();
//            Configuracoes.CaminhoCSVSetBonus = txtSetBonusPath.getText();
            Configuracoes.CaminhoDestinoEstatisticas =txtPathRelatorio.getText();
            Configuracoes.ComportamentoFixo = chkComportamentoFixo.isSelected();
            Configuracoes.BaralhosUsados.clear();
            Configuracoes.BaralhosUsados.add(deck1.getSelectionModel().getSelectedItem().toString());
            Configuracoes.BaralhosUsados.add(deck2.getSelectionModel().getSelectedItem().toString());
            Configuracoes.BaralhosUsados.add(deck3.getSelectionModel().getSelectedItem().toString());
            Configuracoes.BaralhosUsados.add(deck4.getSelectionModel().getSelectedItem().toString());
            Configuracoes.BaralhosUsados.add(deck5.getSelectionModel().getSelectedItem().toString());
            Configuracoes.BaralhosUsados.add(deck6.getSelectionModel().getSelectedItem().toString());
            Configuracoes.BaralhosUsados.add(deck7.getSelectionModel().getSelectedItem().toString());
            Configuracoes.BaralhosUsados.add(deck8.getSelectionModel().getSelectedItem().toString());
            
            Partida novaPartida = new Partida();            
            novaPartida.Jogar();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Partida Concluída");
            a.showAndWait();
        }
        catch(Exception ex)
        {
            LogManager.getLogger(FXMLDocumentController.class).log(Level.FATAL, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(ex.getMessage());
            a.showAndWait();
            
        }
    }

    @FXML
    void initialize() {
        assert txtPathMonsters != null : "fx:id=\"txtPathMonsters\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert chkComportamentoFixo != null : "fx:id=\"chkComportamentoFixo\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
//        assert txtSetBonusPath != null : "fx:id=\"txtSetBonusPath\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtNumPlayers != null : "fx:id=\"txtNumPlayers\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtPathRelatorio != null : "fx:id=\"txtPathRelatorio\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtNumTurns != null : "fx:id=\"txtNumTurns\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtQntItensReve != null : "fx:id=\"txtQntItensReve\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert txtPathItems != null : "fx:id=\"txtPathItems\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        
        List<String> decksPossiveis = Partida.RetornaDecksPossiveis();
        LoadDecksInCombo(deck1, decksPossiveis);
        deck1.getSelectionModel().select(0);
        LoadDecksInCombo(deck2, decksPossiveis);
        deck2.getSelectionModel().select(1);
        LoadDecksInCombo(deck3, decksPossiveis);
        deck3.getSelectionModel().select(2);
        LoadDecksInCombo(deck4, decksPossiveis);
        deck4.getSelectionModel().select(3);
        LoadDecksInCombo(deck5, decksPossiveis);
        deck5.getSelectionModel().select("Nenhum");
        LoadDecksInCombo(deck6, decksPossiveis);
        deck6.getSelectionModel().select("Nenhum");
        LoadDecksInCombo(deck7, decksPossiveis);
        deck7.getSelectionModel().select("Nenhum");
        LoadDecksInCombo(deck8, decksPossiveis);
        deck8.getSelectionModel().select("Nenhum");
        
        txtPathMonsters.setText(Paths.get("").toAbsolutePath().toString() + "\\src\\DataSource\\");
        txtPathItems.setText(Paths.get("").toAbsolutePath().toString() + "\\src\\DataSource\\");
//        txtSetBonusPath.setText(Paths.get("").toAbsolutePath().toString() + "\\src\\DataSource\\SetBonusSummonerPoker.csv");
        
        try {
            txtPathRelatorio.setText(Paths.get("").toRealPath(LinkOption.NOFOLLOW_LINKS).toString() + "\\src\\Relatorios\\");
        } catch (IOException ex) {
            LogManager.getLogger(FXMLDocumentController.class.getName()).log(Level.FATAL,  ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
        

    }
    
    void LoadDecksInCombo(ComboBox field, List<String> decks)
    {
        field.getItems().addAll(decks);
    }
}
