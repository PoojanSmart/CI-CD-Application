/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.RepoCredential;
import entity.RepoMaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
/**
 *
 * @author smart
 */
@Stateless
@LocalBean
public class GitOps {
    public static boolean validateRepo(String url) throws GitAPIException
    {
        final LsRemoteCommand lsCmd = new LsRemoteCommand(null);
        lsCmd.setRemote(url);
        Collection<Ref> call;        
        call = lsCmd.call();
        if(call ==null)
            return false;
        else
            return true;
    }
    public static boolean validateRepo(String url , RepoCredential c) throws GitAPIException
    {
        
        final LsRemoteCommand lsCmd = new LsRemoteCommand(null);
        lsCmd.setRemote(url).setCredentialsProvider(new UsernamePasswordCredentialsProvider(c.getUsername(),c.getPassword()));
        Collection<Ref> call;        
        call = lsCmd.call();
        if(call ==null)
            return false;
        else
            return true;
    }
    
    public static void pollSCM(RepoMaster rp) throws GitAPIException, IOException
    {
        String repourl = rp.getGitId().getRepoUrl();
        RepoCredential cred= rp.getGitId().getCredentialId();
        String physicalPath = rp.getPysicalPath();
        
        if(cred == null)
        {
            if(Files.isDirectory(Paths.get(physicalPath)))
            {
                Repository localRepo = new FileRepository(physicalPath + "/.git");
                Git git = new Git(localRepo);
                git.pull().call();
                git.close();
            }
            else
            {
                Git git = Git.cloneRepository()
                    .setURI(repourl)
                    .setDirectory(new File(physicalPath))
                    .call();   
            }
        }   
        else
        {
            if(Files.isDirectory(Paths.get(physicalPath)))
            {
                Repository localRepo = new FileRepository(physicalPath + "/.git");
                Git git = new Git(localRepo);
                CredentialsProvider cp = new UsernamePasswordCredentialsProvider(cred.getUsername(), cred.getPassword());
                git.pull().setCredentialsProvider(cp).call();
                git.close();
            }
            else
            {
                Git git = Git.cloneRepository()
                    .setURI(repourl)
                    .setDirectory(new File(physicalPath))
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(cred.getUsername(), cred.getPassword()))
                    .call();   
            }
        }

    }
}
