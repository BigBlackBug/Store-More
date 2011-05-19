package qbix.sm.server.aspectj.loggers;

import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import qbix.sm.client.beans.SmFile;

/**
 *
 * @author iliax
 */
@Aspect
public class FileDaoLogger {

    static final Logger log=Logger.getLogger("FileDaoLogger");

    @Around("execution(* qbix.sm.server.dao.FileDao.add(..)))")
    public Object logAddFile(ProceedingJoinPoint pjp) throws Throwable {
        String message="User "+((SmFile)pjp.getArgs()[0]).getCategory().getUser().getName()+
                " is adding new file \""+((SmFile)pjp.getArgs()[0]).getRealName()+"\"";
        Object returN=null;
        try{ returN = pjp.proceed(); }
        catch(Exception ex){
            log.warning("FileDao.add throws "+ex);
            throw ex;
        }
        message+=" => SUCCESS";
        log.info(message);
        return returN;
    }

    @Around("execution(* qbix.sm.server.dao.FileDao.delete(..)))")
    public Object logDeleteFile(ProceedingJoinPoint pjp) throws Throwable {
        String message="User "+((SmFile)pjp.getArgs()[0]).getCategory().getUser().getName()+
                " is deleting file \""+((SmFile)pjp.getArgs()[0]).getRealName()+"\"";
        Object returN=null;
        try{ returN = pjp.proceed(); }
        catch(Exception ex){
            log.warning("FileDao.delete throws "+ex);
            throw ex;
        }
        message+=" => SUCCESS";
        log.info(message);
        return returN;
    }
    
}
