
            @Override
            public synchronized void onNext(Task task) {
                //progressBar.setProgress(progress);
                int n = counter.incrementAndGet();
                float g = (((float) n / 5) * 100);


                Log.d("OnNext", "called from OnNext()" + Thread.currentThread().getName());
                Log.d("Counting", "this is what i've counted" + Integer.toString(n));
                Log.d("percentage", "this is the percentage" + Integer.toString((int) g));
                progressBar.setProgress((int) g);
                textView.setText("" + (int) g + "%");


                Log.d("OnNext: ", "called from OnNext() with a POJO Task named task" + task.getDescription());


            }

            @Override
            public synchronized void onError(Throwable e) {
                Log.d("OnError()", Log.getStackTraceString(e));

            }

            @Override
            public synchronized void onComplete() {

               /* runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Stuff that updates the UI
                       // progressBar.setProgress(100);
                       // textView.setText("" + 100 + "%");

                    }
                });
*/
                //textView.setText("" + 100 + "%");
                launch();
                Log.d("OnComplete", "called from completed()" + Thread.currentThread().getName());
                Log.d("OnComplete()", "everything is completed");

            }