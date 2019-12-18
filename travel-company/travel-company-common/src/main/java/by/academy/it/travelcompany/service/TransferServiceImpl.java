package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.transfer.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TransferServiceImpl implements TransferService {

    private static final TransferService INSTANCE = new TransferServiceImpl();
    private final List<Transfer> transfers = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(10);

    private TransferServiceImpl(){
    }

    public static TransferService getInstance(){
        return INSTANCE;
    }


    @Override
    public List<Transfer> getAllTransfers() {
        return transfers;
    }

    @Override
    public Transfer addTransfer(Transfer transfer) {
        transfer.setId(sequence.incrementAndGet());
        transfers.add(transfer);
        return transfer;
    }

    @Override
    public void deleteTransfer(Long id) {
        transfers.removeIf(t->t.getId().equals(id));
    }

    @Override
    public Transfer updateTransfer(Transfer transfer) {
        deleteTransfer(transfer.getId());
        transfers.add(transfer);
        return transfer;
    }
}
