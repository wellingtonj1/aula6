package aula6;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // clear console
        helper.print("\033[H\033[2J");
        Integer opt = 5;

        // create a hashmap of ArrayList
        // key: randomInt
        // value: ArrayList of Aluno
        java.util.HashMap<Integer, java.util.ArrayList<Aluno>> hashMap = new java.util.HashMap<Integer, java.util.ArrayList<Aluno>>();
        
        // create a arraylist aluno
        while (opt != 0) {

            java.util.ArrayList<Aluno> arrAlunos = new java.util.ArrayList<Aluno>();
            
            helper.print(" 1 - Cadastrar Aluno");
            helper.print(" 2 - Remover Alunos");
            helper.print(" 3 - Listar Aluno");
            helper.print(" 0 - Sair");
            opt = helper.scan().nextInt();

            // generate a random integer 1 to 5
            Integer randomInt = (int) (Math.random() * 5) + 1;
            
            switch (opt) {

                case 1:

                    helper.print("\033[H\033[2J");
                    helper.print("Digite o nome do aluno: ");
                    String name = helper.scan().next();
                    helper.print("Digite a matricula do aluno: ");
                    Integer mat = helper.scan().nextInt();
                    Boolean isError = false;

                    // verify if have existent a aluno with name 
                    for (java.util.Map.Entry<Integer, java.util.ArrayList<Aluno>> entry : hashMap.entrySet()) {
                        
                        for (Aluno aluno : entry.getValue()) {

                            if (aluno.getName().equals(name)) {
                                helper.print("Aluno ja cadastrado");
                                isError = true;
                            }
                        }
                    }

                    if (!isError) {
                        
                        // verify if the randomInt is already in the hashmap
                        if (hashMap.containsKey(randomInt)) {
                            // if the randomInt is already in the hashmap, add the aluno to the arraylist
                            arrAlunos = hashMap.get(randomInt);
                            arrAlunos.add(new Aluno(name, mat));
                            hashMap.put(randomInt, arrAlunos);
                        } else {
                            // if the randomInt is not in the hashmap, create a new arraylist and add the aluno to the arraylist
                            arrAlunos.add(new Aluno(name, mat));
                            hashMap.put(randomInt, arrAlunos);
                        }
                    } else {
                        helper.print("Erro ao cadastrar aluno");
                    }
                    
                    break;
                
                case 2:
                    
                    helper.print("\033[H\033[2J");
                    helper.print("Digite o nome do aluno: ");
                    name = helper.scan().next();
                    Aluno toRemove = new Aluno(name, 0);
                    Integer key = 0;

                    // remove the aluno from the arraylist inside the hashmap
                    for (java.util.Map.Entry<Integer, java.util.ArrayList<Aluno>> entry : hashMap.entrySet()) {
                        java.util.ArrayList<Aluno> arrAlunos2 = entry.getValue();
                        for (Aluno aluno : arrAlunos2) {
                            if (aluno.getName().equals(name)) {
                                // remove the aluno from the arraylist
                                toRemove = aluno;
                                // get key
                                key = entry.getKey();
                            }
                        }
                    }

                    // remove aluno from hashmap by key and arraylist
                    hashMap.get(key).remove(toRemove);

                    // if the arraylist is empty, remove the key from the hashmap
                    if (hashMap.get(key).isEmpty()) {
                        hashMap.remove(key);
                    }

                    break;

                case 3:
                
                    // verify if is empty
                    if (hashMap.isEmpty()) {
                        helper.print("HashMap is empty");
                    } else {
                        // walk in hashmap
                        for (java.util.Map.Entry<Integer, java.util.ArrayList<Aluno>> entry : hashMap.entrySet()) {
                            // print the key
                            helper.print("Key: " + entry.getKey());
                            // print the value
                            for (Aluno aluno : entry.getValue()) {
                                helper.print("Aluno: " + aluno.getName() + " - " + aluno.getMat());
                            }
                        }

                    }

                    break;

                default:
                    break;
            }

        }

        // remove all in hashmap
        hashMap.clear();
        
    }
}
