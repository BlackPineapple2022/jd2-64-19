package by.academy.it.travelcompany.service.features;

import by.academy.it.travelcompany.travelitem.features.transfer.Transfer;

import java.util.List;

/**
 * Transfer service
 */

public interface TransferService {

    /**
     * Get all transfers
     * @return list of found flights
     */

    List<Transfer> getAllTransfers();

    /**
     * Add new transfer
     * @param transfer transfer to save
     * @return new transfer with generated id
     */

    Transfer addTransfer(Transfer transfer);

    /**
     * Delete transfer by id
     * transfer with @param id delete from list
     */

    void deleteTransfer(Long id);

    /**
     * update object in list with same id of
     * @param transfer transfer to update (by id)
     * @return transfer updated transfer
     */

    Transfer updateTransfer(Transfer transfer);

}
