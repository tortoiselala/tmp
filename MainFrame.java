import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author tortoiselala
 */
public class MainFrame extends JFrame {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                MainFrame test = new MainFrame();
                test.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private JButton loadData;
    private JButton exit;
    private JTextField queryField;
    private JButton query;
    private JPanel panelCenter;
    private String[][] datas;
    private JScrollPane scrollPane;

    public MainFrame() {
        try{
            init();
            setContents();
//            load();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "内部错误", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void init() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // 设置窗口使用默认关闭方式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置系统默认位置
        setLocation(Constants.MAIN_WINDOW_X_POSITION, Constants.MAIN_WINDOW_Y_POSITION);
        // 设置窗口默认大小
        setSize(Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
        // 设置窗口大小不能改变
        setResizable(false);
    }

    public void setContents(){
        JPanel panelUp = new JPanel();
        this.setLayout(new BorderLayout());
        this.loadData = new JButton("读取数据");
        this.loadData.setVerticalTextPosition(AbstractButton.CENTER);
        loadData.addActionListener(e -> {
            System.out.println("load data button");
            load();
        });

        this.exit = new JButton("退出");
        this.exit.setVerticalTextPosition(AbstractButton.CENTER);
        this.exit.addActionListener(e -> System.exit(0));
        panelUp.add(this.loadData);
        panelUp.add(this.exit);
        panelUp.setSize(Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT / 10);

        JPanel panelCenter = new JPanel();
        this.panelCenter = panelCenter;
        this.panelCenter.setSize(Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT / 10 * 8);
        this.panelCenter.setLayout(new BorderLayout());

        JPanel panelDown = new JPanel();
        this.queryField = new JTextField();
        this.queryField.setPreferredSize(new Dimension(Constants.MAIN_WINDOW_WIDTH / 10, Constants.MAIN_WINDOW_HEIGHT / 30));
        this.query = new JButton("查询");
        this.query.addActionListener(e->{
            String key = this.queryField.getText();
            boolean status = false;

            if(this.datas == null){
                JOptionPane.showMessageDialog(null,
                        "请先点击读取数据按钮，获取数据",
                        "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            for(String[] line : this.datas){
                if(line[0].equals(key)){
                    status = true;
                    JOptionPane.showMessageDialog(null,
                            "姓名：" + line[1]
                            + ", 数学" + line[2]
                            + ", 英语" + line[3]
                            + ", 计算机" + line[4]
                            + ", 总分" + line[5],
                            "学生：" + key + "的成绩信息", JOptionPane.INFORMATION_MESSAGE);

                }
            }
            if(!status){
                JOptionPane.showMessageDialog(null,
                        "查无此人",
                        "查询条件错误", JOptionPane.INFORMATION_MESSAGE);

            }

        });


        panelDown.add(this.queryField);
        panelDown.add(this.query);
        panelDown.setSize(Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT / 10);

        this.add(panelUp, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelDown, BorderLayout.SOUTH);
    }

    private void load(){
        try{
            List<Student> list = StudentInfoLoader.loader("./src/stu.dat");
            String[][] datas = new String[list.size()][6];
            int i = 0;
            for(Student stu : list){
                datas[i][0] = stu.getUid();
                datas[i][1] = stu.getName();
                datas[i][2] = String.valueOf(stu.getMath());
                datas[i][3] = String.valueOf(stu.getEnglish());
                datas[i][4] = String.valueOf(stu.getComputer());
                datas[i][5] = String.valueOf(stu.getTotal());
                ++i;
            }
            this.datas = datas;
            JTable table = null;
            DefaultTableModel model = new DefaultTableModel(datas, new String[]{"学号", "姓名", "英语", "数学", "计算机", "总分"});
            table = new JTable(model);
            table.setPreferredScrollableViewportSize(new Dimension(550, 100));
            table.setBounds(10, 10, Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT / 10 * 6);
            this.panelCenter.removeAll();
            this.scrollPane = new JScrollPane(table);

            this.panelCenter.add(this.scrollPane, BorderLayout.CENTER);
            this.panelCenter.updateUI();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "内部错误", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
