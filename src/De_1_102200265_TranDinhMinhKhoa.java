import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class De_1_102200265_TranDinhMinhKhoa {
    private JPanel mainPanel;
    private JButton importButton;
    private JTextField textField1;
    private JTextField keywordTextField;
    private JButton soLuongButton;
    private JButton tongTienButton;
    private JButton goiYButton;
    private JTextArea textArea1;
    private Connection connection;

    public De_1_102200265_TranDinhMinhKhoa() {
        soLuongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = keywordTextField.getText();
                int count = 0;
                try {
                    String sql = "SELECT * FROM donhang WHERE tenmathang = '" + keyword + "'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        count++;
                    }
                    textArea1.setText(String.valueOf(count));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String connectionString = "jdbc:postgresql://localhost:5432/concu?user=postgres&password=140521";
                    connection = DriverManager.getConnection(connectionString);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        tongTienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sum = 0;
                String keyword = keywordTextField.getText();
                try {
                    String sql = "SELECT * FROM donhang WHERE tennguoimua = '" + keyword + "'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        sum += resultSet.getInt("soluong");
                    }
                    textArea1.setText(String.valueOf(sum));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        goiYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = keywordTextField.getText();
                List<String> nguoiMua = new ArrayList<>();
                try {
                    String sql = "SELECT * FROM donhang WHERE tenmathang = '" + keyword + "'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        nguoiMua.add(resultSet.getString("tennguoimua"));
                    }
                    Map<String, Integer> mapKetQua = new HashMap<String, Integer>();

                    for (String nguoiMua1 : nguoiMua) {
                        sql = "SELECT * FROM donhang WHERE tennguoimua = '" + nguoiMua1 + "'";
                        statement = connection.createStatement();
                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            if (resultSet.getString("tenmathang").equals(keyword)) {

                            } else {
                                if (mapKetQua.containsKey(resultSet.getString("tenmathang"))) {
                                    mapKetQua.put(resultSet.getString("tenmathang"), mapKetQua.get(resultSet.getString("tenmathang")) + resultSet.getInt("soluong"));
                                } else {
                                    mapKetQua.put(resultSet.getString("tenmathang"), resultSet.getInt("soluong"));
                                }
                            }
                        }
                        //convert mapKetQua to string
                        String ketQua = "";
                        for (Map.Entry<String, Integer> entry : mapKetQua.entrySet()) {
                            ketQua += entry.getKey() + " " + entry.getValue() + "\n";
                        }
                        textArea1.setText(ketQua);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("De_1_102200265_TranDinhMinhKhoa");
        frame.setContentPane(new De_1_102200265_TranDinhMinhKhoa().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setText("Label");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(label1, gbc);
        importButton = new JButton();
        importButton.setText("Import");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(importButton, gbc);
        textField1 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(textField1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Keyword");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(label2, gbc);
        keywordTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(keywordTextField, gbc);
        soLuongButton = new JButton();
        soLuongButton.setText("So luong");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(soLuongButton, gbc);
        tongTienButton = new JButton();
        tongTienButton.setText("Tong tien");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(tongTienButton, gbc);
        goiYButton = new JButton();
        goiYButton.setText("Goi y");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(goiYButton, gbc);
        textArea1 = new JTextArea();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(textArea1, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
