package thread.fork;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zhuang.ma
 * @date 2022/1/25
 */
@Slf4j
public class ForkJoinPoolTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool
                (10,
                        ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                        null, true);


        ForkJoinTask<String> submit = forkJoinPool.submit(new UserForkJoin(1, 10));
        System.out.println(submit.get());
        getUserName();

    }

    static class UserForkJoin extends RecursiveTask<String> {

        private static final long serialVersionUID = -6556159885753771416L;

        int start;
        int end;

        public UserForkJoin(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected String compute() {
            int count = start - end;
            if (count == 0) {
                String userName = getUserName();
                log.info("userName {}", userName);
                return userName;
            } else {
                int x = (start + end) / 2;
                UserForkJoin userForkJoin = new UserForkJoin(start, x);
                userForkJoin.fork();
                UserForkJoin userForkJoin2 = new UserForkJoin(x+1, end);
                userForkJoin2.fork();
                return userForkJoin.join() + userForkJoin2.join();
            }

        }


    }

    private static String getUserName() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "zhang";
    }


}
