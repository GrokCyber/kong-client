package cn.edu.gdut.zaoying.kong;


import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class KongClient<T> {

    private FutureTask<T> futureTask;

    public KongClient(FutureTask<T> futureTask) {
        this.futureTask = futureTask;
    }

    public static <T> KongClient<T> call(Callable<T> callable){
        return new KongClient<>(new FutureTask<>(callable));
    }

    public static KongClient<Void> call(Runnable runnable){
        return call(()->{runnable.run();return null;});
    }

    public T execute(){
        try {
            ExecutorUtils.execute(futureTask);
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            if(e.getCause() instanceof KongException){
                throw (KongException)e.getCause();
            }
            throw new KongException("网关超时", e);
        }
    }

    public Optional<T> execute(ErrorHandler<T> errorHandler){
        Optional<T> optional;
        try {
            T result = execute();
            optional = Optional.ofNullable(result);
        }
        catch (KongException kong){
            T result = errorHandler.handle(kong);
            optional = Optional.ofNullable(result);
        }
        return optional;
    }

    public interface ErrorHandler<T>{
        T handle(KongException kong);
    }
}
