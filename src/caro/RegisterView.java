package caro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterView extends JFrame implements ActionListener {
    private JTextField txtUsernameRegister;
    private JPasswordField txtPasswordRegister;
    private JTextField txtDisplayName;
    private JTextField txtEmail;
    private JButton btnRegister;
    private JButton btnBackToLogin;

    public RegisterView() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Đăng Kí Tài Khoản");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gridBagConstraints;

        JLabel lblDisplayName = new JLabel("Tên hiển thị:");
        lblDisplayName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(20, 20, 10, 20);
        panel.add(lblDisplayName, gridBagConstraints);

        txtDisplayName = new JTextField(20);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(20, 20, 10, 20);
        panel.add(txtDisplayName, gridBagConstraints);

        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 20, 10, 20);
        panel.add(lblUsername, gridBagConstraints);

        txtUsernameRegister = new JTextField(20);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 20, 10, 20);
        panel.add(txtUsernameRegister, gridBagConstraints);

        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(10, 20, 10, 20);
        panel.add(lblPassword, gridBagConstraints);

        txtPasswordRegister = new JPasswordField(20);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(10, 20, 10, 20);
        panel.add(txtPasswordRegister, gridBagConstraints);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(10, 20, 10, 20);
        panel.add(lblEmail, gridBagConstraints);

        txtEmail = new JTextField(20);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(10, 20, 10, 20);
        panel.add(txtEmail, gridBagConstraints);

        btnRegister = new JButton("Đăng Kí");
        btnRegister.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnRegister.setBackground(new Color(87, 184, 70));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(20, 20, 10, 20);
        panel.add(btnRegister, gridBagConstraints);

        btnBackToLogin = new JButton("Quay lại đăng nhập");
        btnBackToLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnBackToLogin.setForeground(new Color(0, 132, 255));
        btnBackToLogin.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(10, 20, 20, 20);
        panel.add(btnBackToLogin, gridBagConstraints);

        add(panel);
        pack();
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            register();
        } else if (e.getSource() == btnBackToLogin) {
            backToLogin();
        }
    }
    private boolean validateForm() {
        String displayName = txtDisplayName.getText();
        String username = txtUsernameRegister.getText();
        String password = new String(txtPasswordRegister.getPassword());
        String email = txtEmail.getText();

        if (displayName.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
            return false;
        }

        // Kiểm tra tên tài khoản đã tồn tại hay chưa
        if (isUsernameExists(username)) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại!");
            return false;
        }

        // Kiểm tra độ dài của mật khẩu
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải chứa ít nhất 6 ký tự!");
            return false;
        }

        // Kiểm tra định dạng của email
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Địa chỉ email không hợp lệ!");
            return false;
        }

        return true;
    }
    private boolean isUsernameExists(String username) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT COUNT(*) AS count FROM user WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0; // Trả về true nếu tên tài khoản đã tồn tại trong cơ sở dữ liệu
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra tên tài khoản: " + ex.getMessage());
        }
        return false; // Trả về false nếu có lỗi xảy ra hoặc tên tài khoản không tồn tại
    }

    private boolean isValidEmail(String email) {
        // Kiểm tra định dạng email bằng regular expression
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches(); // Trả về true nếu địa chỉ email hợp lệ, ngược lại trả về false
    }

    private void register() {
        if (validateForm()) {
            String displayName = txtDisplayName.getText();
            String username = txtUsernameRegister.getText();
            String password = new String(txtPasswordRegister.getPassword());
            String email = txtEmail.getText();
            String hashedPassword = hashPassword(password);

            try {
                Connection connection = DatabaseConnection.getConnection();
                String sql = "INSERT INTO user (username, password, email, displayname) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, hashedPassword);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, displayName);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Đăng kí tài khoản thành công!");
                    backToLogin();
                } else {
                    JOptionPane.showMessageDialog(this, "Đăng kí tài khoản thất bại!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi thực hiện đăng kí tài khoản: " + ex.getMessage());
            }
        }
    }
    private String hashPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    private void backToLogin() {
        this.dispose();
        new LoginView();
    }

}
