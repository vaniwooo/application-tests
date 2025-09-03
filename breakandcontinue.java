public class breakandcontinue {

    public static void main(String[] args) {
        // break - stops the loop
        // continue - stops, pero it continues
        for (int i = 0; i < 15; i++) {
            if (i == 4) {
                continue;
            };
            System.out.println(i);
        }
        String[] names = {"martin", "zel", "aly", "balba"};
        int[] ages = {1, 3 ,5 ,6};

        System.out.println(names[0]);

        // loops
        for (String name : names) {
            System.out.println(name);
        }
    }
}