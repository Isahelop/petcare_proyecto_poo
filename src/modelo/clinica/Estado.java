package modelo.clinica;

/**
 * Estados posibles de una cita de la clinica.
 */
public enum Estado {
    /** La cita fue agendada y aun no se atiende. */
    PROGRAMADA,
    /** La cita ya se atendio y tiene un servicio asociado. */
    ATENDIDA,
    /** La cita fue cancelada y ya no puede atenderse. */
    CANCELADA
}