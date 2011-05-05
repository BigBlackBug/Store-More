package qbix.sm.server.aspectj.loggers;

import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import qbix.sm.client.beans.SmCategory;

/**
 *
 * @author iliax
 */
@Aspect
public class CategoryDaoLogger {
    
    static final Logger log=Logger.getLogger("CategoryDaoLoggerr");

    @Around("execution(* qbix.sm.server.dao.CategoryDao.add(..)))")
    public Object logAddCategory(ProceedingJoinPoint pjp) throws Throwable {
        String message="User "+((SmCategory)pjp.getArgs()[0]).getUser().getName()+
                " is adding new category \""+((SmCategory)pjp.getArgs()[0]).getName()+"\"";
        Object returN=null;
        try{ returN = pjp.proceed(); }
        catch(Exception ex){
            log.warning("CategoryDao.add throws "+ex);
            throw ex;
        }
        message+=" => SUCCESS";
        log.info(message);
        return returN;
    }

    @Around("execution(* qbix.sm.server.dao.CategoryDao.delete(..)))")
    public Object logDeleteCategory(ProceedingJoinPoint pjp) throws Throwable {
        String message="User "+((SmCategory)pjp.getArgs()[0]).getUser().getName()+
                " is deleting category \""+((SmCategory)pjp.getArgs()[0]).getName()+"\"";
        Object returN=null;
        try{ returN = pjp.proceed(); }
        catch(Exception ex){
            log.warning("CategoryDao.delete throws "+ex);
            throw ex;
        }
        message+=" => SUCCESS";
        log.info(message);
        return returN;
    }

    //логировать остальное пока смысла нет, тк не думаю что будем использовать все методы дао
    
}
