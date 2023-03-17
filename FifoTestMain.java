package tp.structurededonnees.FIFO;

import tp.structurededonnees.FIFO.FifoTest;

public class FifoTestMain {
    public static void main(String[] args) {
        FifoTest fifoTest = new FifoTest();
        fifoTest.testFifoCapacity(); // Test la capacité négative de Fifo
        fifoTest.testFifoCapacity2(); // Test la capacité 0 de Fifo
        fifoTest.testEmpty(); // Test le retrait sur une Fifo vide
        fifoTest.testFull(); // Test l'ajout d'un élément dans une Fifo pleine
        fifoTest.testOfferNull(); // Test l'ajout d'un élément null dans une Fifo
        fifoTest.testOfferPoll(); // Test l'ajout et le retrait de plusieurs éléments dans une Fifo
        fifoTest.testFullToEmpty(); // Test l'ajout d'un élément dans une Fifo pleine et le retrait de tous les éléments
        fifoTest.testSize(); // Test la taille de la Fifo
        fifoTest.testSizeEmpty(); // Test la taille de la Fifo vide
        fifoTest.testSizeFull(); // Test la taille de la Fifo pleine
        fifoTest.testIsEmpty(); // Test si la Fifo est vide
        fifoTest.testEmptyToString(); // Test la conversion de la Fifo vide en chaîne de caractères
        fifoTest.testOneElementToString(); // Test la conversion d'une Fifo à un élément en chaîne de caractères
        fifoTest.testTwoElementToString(); // Test la conversion d'une Fifo à deux éléments en chaîne de caractères
        fifoTest.testNonMutateToString(); // Test la conversion d'une Fifo en chaîne de caractères sans altérer la Fifo
        fifoTest.testFullToString(); // Test la conversion d'une Fifo pleine en chaîne de caractères
    }
}