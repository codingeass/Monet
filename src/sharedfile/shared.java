/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sharedfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
/**
 *
 * @author Amandeep
 */
public class shared extends javax.swing.JFrame {

    /**
     * Creates new form shared
     */
    DefaultMutableTreeNode treeNode1;
    DefaultMutableTreeNode treeNode2;
    DefaultMutableTreeNode treeNode3;
    String operation="continue";
    public shared() {
        initComponents();
        treeNode1 = new DefaultMutableTreeNode("Network");
        
        jTree1.setModel(new DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);
    }
    /**
     * issues : permission issue on file
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        searchIp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Shared File Check");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        searchIp.setText("Search");
        searchIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(searchIp, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(searchIp)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
class StreamGobbler
{
    InputStream is;
    String type;
    
    StreamGobbler(InputStream is, String type)
    {
        this.is = is;
        this.type = type;
    }
    
    public void run()
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null)
            {
                System.out.println(type + ">" + line);                
               }
            } catch (IOException ioe)
              {
                ioe.printStackTrace();  
              }
    }
}
    @SuppressWarnings("empty-statement") 
    synchronized void  netview(String ip) 
{
    Runtime rt = Runtime.getRuntime();
    try{
        final Process proc;
      //  synchronized(this){
     proc =rt.exec("net view \\\\"+ip);  
// this.wait(100);
       // }
 /*
     SECONDS.timedJoin(
    new Thread() {
      public void run() {
    StreamGobbler outputGobbler = new 
              StreamGobbler(proc.getInputStream(), "OUTPUT");
       StreamGobbler errorGobbler = new 
                StreamGobbler(proc.getErrorStream(), "ERROR");                        
                  errorGobbler.run();
            outputGobbler.run();        
      }
    },
    1);  
*/
        
    InputStreamReader isr;
    BufferedReader br;
    try{
            isr = new InputStreamReader(proc.getInputStream());
             br = new BufferedReader(isr);
            String line=null;  
            int i=0;
            while ( (line = br.readLine()) != null)
            { 
                ++i; 
              try{
                if(line.trim().equals("The command completed successfully."))
                        break;
                if(i==8)
                {
                      treeNode2 = new DefaultMutableTreeNode(ip);                        
                }
                if(i>=8)
                {
                    String[] splited = line.split("\\s+");                    
                    String display="";
                    int l=0;
                    while(l+1<splited.length)
                    {
                        display=display+" "+splited[l];
                        l++;
                    }
                    treeNode3 = new DefaultMutableTreeNode(display);
                    treeNode2.add(treeNode3);
                    treeNode1.add(treeNode2); 
                    jTree1.setModel(new DefaultTreeModel(treeNode1));
                }
              }
              catch(Exception e)
              {
                  System.out.println(""+e);
              }
                System.out.println(i+line);                                                    
            }
            } 
            catch (IOException ioe)
            {
               ioe.printStackTrace();  
            }
     }
  catch(Exception e)
  {
      System.out.println(""+e);
  }
}
    private void searchIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchIpActionPerformed
try{
    Runtime rt = Runtime.getRuntime();
    Process proc =rt.exec("arp -a");
             StreamGobbler errorGobbler = new 
                StreamGobbler(proc.getErrorStream(), "ERROR");                        
            InputStreamReader isr;
    BufferedReader br;        
    // StreamGobbler outputGobbler = new 
             //  StreamGobbler(proc.getInputStream(), "OUTPUT");            
        int i=0;        
        final String[] allip=new String[100];         
        try{
            isr = new InputStreamReader(proc.getInputStream());
             br = new BufferedReader(isr);
            String line;
            while ( (line = br.readLine()) != null)
            { 
                try{
                    String[] splited = line.split("\\s+");
                    //System.out.println(splited[1]);
                    allip[i]=splited[1];
                    i++;
                }
                catch(Exception e)
                {
                    System.out.println(""+e);
                }                
            }
            } 
            catch (IOException ioe)
            {
               ioe.printStackTrace();  
            }
       final int k=i; 
        Thread thread = new Thread(){
    public void run(){
        int j=0;
       searchIp.setEnabled(false);
       searchIp.setText("Searching");
       if(allip[0].equals("ARP"))
       {
           JOptionPane.showMessageDialog(null,"You are not Connected");
           searchIp.setEnabled(true);
           searchIp.setText("Search");
           return;
       }
      while(j<k)
        {
            System.out.println(""+allip[j]);
            netview(allip[j]);
            if(operation.equals("stop"))
                break;
          //  Thread.currentThread().sleep(1000);
            j++;
        }
      searchIp.setEnabled(true);
      searchIp.setText("Search");
    }
  };
    thread.start();    
             errorGobbler.run();
            //outputGobbler.start();
}
catch(Exception e){
    JOptionPane.showMessageDialog(null,"Error occurred or invalid Entry"+e);
}        // TODO add your handling code here:
    }//GEN-LAST:event_searchIpActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
if(evt.getClickCount()==2)
{
    int[] a=jTree1.getSelectionRows();
    TreePath path=jTree1.getPathForRow(a[0]);
    
    String ip=(""+path.getPathComponent(1)).trim();
    String lastpath=(""+path.getLastPathComponent()).trim();
    String location="\\\\"+ip;
    if(!ip.equals(lastpath))
    {
        location+="\\"+lastpath;        
    }
     Runtime rt = Runtime.getRuntime();
     try{
        Process proc =rt.exec("explorer "+location);
     } catch (IOException ex) {
        Logger.getLogger(shared.class.getName()).log(Level.SEVERE, null, ex);
     }
}
// TODO add your handling code here:
    }//GEN-LAST:event_jTree1MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
operation="stop";
// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(shared.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(shared.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(shared.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(shared.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new shared().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JButton searchIp;
    // End of variables declaration//GEN-END:variables
}
